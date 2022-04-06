package com.fabledt5.game.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fabledt5.common.theme.DarkLateGray
import com.fabledt5.common.theme.Mark
import com.fabledt5.common.theme.Proxima
import com.fabledt5.common.theme.SandyBrown
import com.fabledt5.domain.model.ReviewItem
import com.fabledt5.game.R

@Composable
fun GameReviewItem(reviewItem: ReviewItem, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = DarkLateGray
    ) {
        Column(
            modifier = Modifier
                .padding(all = 10.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = reviewItem.reviewerName,
                        color = Color.White,
                        fontFamily = Mark,
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp
                    )
                    Spacer(modifier = Modifier.height(3.dp))
                    Text(
                        text = reviewItem.reviewDate,
                        color = Color.Gray,
                        fontFamily = Proxima,
                        fontSize = 10.sp
                    )
                }
                Row {
                    repeat(times = 5 - reviewItem.reviewerRating) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star_outlined),
                            contentDescription = stringResource(R.string.icon_star),
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .size(10.dp),
                            tint = SandyBrown
                        )
                    }
                    repeat(times = reviewItem.reviewerRating) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_star_filled),
                            contentDescription = stringResource(R.string.icon_star),
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .size(10.dp),
                            tint = SandyBrown
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = reviewItem.reviewText,
                color = Color.White.copy(alpha = .7f),
                fontFamily = Proxima,
                fontSize = 12.sp
            )
        }
    }
}