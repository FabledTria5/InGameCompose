package com.fabledt5.repository.firebase

import com.fabledt5.domain.model.DefaultErrors
import com.fabledt5.domain.model.GameType
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import com.fabledt5.domain.repository.ErrorRepository
import com.fabledt5.domain.repository.firebase.FireStoreRepository
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FireStoreRepositoryImpl @Inject constructor(
    private val uId: String,
    private val firestore: FirebaseFirestore,
    private val errorRepository: ErrorRepository,
) : FireStoreRepository {

    companion object {
        private const val USERS_TABLE_NAME = "users"
        private const val FAVORITES_TABLE_NAME = "favorite_games"
        private const val PLAYED_TABLE_NAME = "played_games"

        private const val USER_EMAIL = "email"
        private const val USER_NICKNAME = "nickname"

        private const val GAME_ID = "game_id"
        private const val GAME_TITLE = "game_title"
        private const val GAME_POSTER = "game_poster"
        private const val GAME_DEVELOPER = "game_developer"
        private const val RELEASE_DATE = "release_date"
    }

    override suspend fun createUser(
        userEmail: String,
        userNickname: String,
    ) = try {
        val documentReference = firestore.collection(USERS_TABLE_NAME).document(uId)
        val userData = hashMapOf(USER_EMAIL to userEmail, USER_NICKNAME to userNickname)
        documentReference.set(userData).await()
        Resource.Success(data = true)
    } catch (exception: FirebaseFirestoreException) {
        Timber.e(exception)
        val error = errorRepository.resolveRemoteUserDataError(exception)
        Resource.Error(error)
    }

    override fun updateFavoriteGames() = callbackFlow {
        val collectionRef = firestore
            .collection(USERS_TABLE_NAME)
            .document(uId)
            .collection(FAVORITES_TABLE_NAME)

        val observer = collectionRef.addSnapshotListener { games, error ->
            error?.let {
                Timber.e(it)
                trySend(
                    Resource.Error(
                        error = errorRepository.resolveRemoteUserDataError(error)
                    )
                )
                return@addSnapshotListener
            }

            games!!.documentChanges.forEach { documentChange ->
                when (documentChange.type) {
                    DocumentChange.Type.REMOVED -> {
                        val gameItem = getGame(documentChange.document.data)
                        trySend(
                            Resource.Error(
                                error = DefaultErrors.EmptyError(),
                                data = gameItem to GameType.FAVORITE
                            )
                        )
                    }
                    else -> {
                        val gameItem = getGame(documentChange.document.data)
                        trySend(Resource.Success(data = gameItem to GameType.FAVORITE))
                    }
                }
            }
        }
        awaitClose {
            observer.remove()
        }
    }

    override fun updatePlayedGames() = callbackFlow {
        val collectionRef = firestore
            .collection(USERS_TABLE_NAME)
            .document(uId)
            .collection(PLAYED_TABLE_NAME)

        val observer = collectionRef.addSnapshotListener { games, error ->
            error?.let {
                Timber.e(it)
                trySend(
                    Resource.Error(
                        error = errorRepository.resolveRemoteUserDataError(error)
                    )
                )
                return@addSnapshotListener
            }

            games!!.documentChanges.forEach { documentChange ->
                when (documentChange.type) {
                    DocumentChange.Type.REMOVED -> {
                        val gameItem = getGame(documentChange.document.data)
                        trySend(
                            Resource.Error(
                                error = DefaultErrors.EmptyError(),
                                data = gameItem to GameType.PLAYED
                            )
                        )
                    }
                    else -> {
                        val gameItem = getGame(documentChange.document.data)
                        trySend(Resource.Success(data = gameItem to GameType.PLAYED))
                    }
                }
            }
        }
        awaitClose {
            observer.remove()
        }
    }

    override suspend fun addGameToCollection(gameItem: GameItem, gameType: GameType) {
        val gameDocument = hashMapOf(
            GAME_ID to gameItem.gameId,
            GAME_TITLE to gameItem.gameTitle,
            GAME_POSTER to gameItem.gamePoster,
            GAME_DEVELOPER to gameItem.gameDeveloper,
            RELEASE_DATE to gameItem.releaseDate
        )

        gameType.toCollection()?.let { type ->
            firestore.collection(USERS_TABLE_NAME)
                .document(uId)
                .collection(type)
                .document(gameItem.gameId.toString())
                .set(gameDocument)
        }
    }

    override suspend fun removeGameFromCollection(gameId: Int, gameType: GameType) {
        gameType.toCollection()?.let { type ->
            firestore.collection(USERS_TABLE_NAME)
                .document(uId)
                .collection(type)
                .document(gameId.toString())
                .delete()
        }
    }

    private fun getGame(gameData: Map<String, Any>): GameItem {
        return GameItem(
            gameId = (gameData[GAME_ID] as Long).toInt(),
            gameTitle = (gameData[GAME_TITLE] as? String).orEmpty(),
            gamePoster = (gameData[GAME_POSTER] as? String).orEmpty(),
            gameDeveloper = (gameData[GAME_DEVELOPER] as? String).orEmpty(),
            releaseDate = (gameData[RELEASE_DATE] as? String).orEmpty()
        )
    }

    private fun GameType.toCollection() = when (this) {
        GameType.FAVORITE -> FAVORITES_TABLE_NAME
        GameType.PLAYED -> PLAYED_TABLE_NAME
        GameType.HOT_GAME -> null
    }
}