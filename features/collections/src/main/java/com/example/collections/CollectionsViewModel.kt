package com.example.collections

import androidx.compose.runtime.mutableStateMapOf
import androidx.lifecycle.ViewModel
import com.fabledt5.domain.model.Resource
import com.fabledt5.domain.model.items.GameItem
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.inject.Inject

@HiltViewModel
class CollectionsViewModel @Inject constructor() : ViewModel() {

    companion object {
        private const val CALENDAR_DATES_FORMAT = "dd MMM. yyyy"
    }

    val calendarGamesMap = mutableStateMapOf<String, Resource<List<GameItem>>>()

    init {
        dateSelected(LocalDate.now())
    }

    fun dateSelected(localDate: LocalDate) {
        val formattedDate =
            localDate.format(DateTimeFormatter.ofPattern(CALENDAR_DATES_FORMAT, Locale.ENGLISH))
        if (calendarGamesMap.containsKey(formattedDate)) calendarGamesMap.remove(formattedDate)
        else {
            calendarGamesMap[formattedDate] = Resource.Success(
                listOf(
                    GameItem(
                        gameId = -1,
                        gamePoster = "https://cdn1.epicgames.com/offer/0a9e3c5ab6684506bd624a849ca0cf39/EGS_DeathStrandingDirectorsCut_KOJIMAPRODUCTIONS_S3_2560x1440-fe4e51f1801fba36e452aa3466625789",
                        gameTitle = "Death Stranding",
                        gameLastUpdate = "17 Dec. 2020",
                    ),
                    GameItem(
                        gameId = -1,
                        gamePoster = "https://cdn1.epicgames.com/offer/0a9e3c5ab6684506bd624a849ca0cf39/EGS_DeathStrandingDirectorsCut_KOJIMAPRODUCTIONS_S3_2560x1440-fe4e51f1801fba36e452aa3466625789",
                        gameTitle = "Death Stranding",
                        gameLastUpdate = "17 Dec. 2020",
                    ),
                    GameItem(
                        gameId = -1,
                        gamePoster = "https://cdn1.epicgames.com/offer/0a9e3c5ab6684506bd624a849ca0cf39/EGS_DeathStrandingDirectorsCut_KOJIMAPRODUCTIONS_S3_2560x1440-fe4e51f1801fba36e452aa3466625789",
                        gameTitle = "Death Stranding",
                        gameLastUpdate = "17 Dec. 2020",
                    ),
                    GameItem(
                        gameId = -1,
                        gamePoster = "https://cdn1.epicgames.com/offer/0a9e3c5ab6684506bd624a849ca0cf39/EGS_DeathStrandingDirectorsCut_KOJIMAPRODUCTIONS_S3_2560x1440-fe4e51f1801fba36e452aa3466625789",
                        gameTitle = "Death Stranding",
                        gameLastUpdate = "17 Dec. 2020",
                    ),
                    GameItem(
                        gameId = -1,
                        gamePoster = "https://cdn1.epicgames.com/offer/0a9e3c5ab6684506bd624a849ca0cf39/EGS_DeathStrandingDirectorsCut_KOJIMAPRODUCTIONS_S3_2560x1440-fe4e51f1801fba36e452aa3466625789",
                        gameTitle = "Death Stranding",
                        gameLastUpdate = "17 Dec. 2020",
                    )
                )
            )
        }
    }

}