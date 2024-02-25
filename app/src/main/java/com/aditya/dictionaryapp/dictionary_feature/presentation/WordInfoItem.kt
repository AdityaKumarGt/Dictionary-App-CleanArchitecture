package com.aditya.dictionaryapp.dictionary_feature.presentation

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aditya.dictionaryapp.R
import com.aditya.dictionaryapp.core.util.AudioPlayer
import com.aditya.dictionaryapp.dictionary_feature.domain.model.WordInfo
import com.aditya.dictionaryapp.ui.theme.BoxBackground
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun WordInfoItem(
    wordInfo: WordInfo, modifier: Modifier = Modifier
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(10.dp)) // This gives the box rounded corners
            .background(BoxBackground)
            .padding(10.dp)

    ) {
        Column(modifier = modifier) {
            wordInfo.word?.let {
                Text(
                    text = it, fontSize = 24.sp, fontWeight = FontWeight.Bold, color = Color.Black
                )
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                wordInfo?.phonetics?.firstOrNull()?.audio?.let{
                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.sound))
                    var isPlaying by remember { mutableStateOf(false) }

                    LottieAnimation(
                        composition = composition,
                        modifier = Modifier
                            .size(40.dp)
                            .clickable {
                                isPlaying = !isPlaying
                                val audioPlayer = AudioPlayer(it)
                                audioPlayer.start()
                            },
                        progress = animateLottieCompositionAsState(
                            composition = composition, isPlaying = isPlaying
                        ).value,
                    )
                }

                wordInfo.phonetic?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Light,
                    )
                }
            }


            Spacer(modifier = Modifier.height(16.dp))

            wordInfo.meanings?.forEach { meaning ->
                meaning.partOfSpeech?.let { Text(text = it, fontWeight = FontWeight.Bold) }

                meaning.definitions?.forEachIndexed { i, definition ->
                    Text(text = "${i + 1}. ${definition.definition}")
                    Spacer(modifier = Modifier.height(8.dp))
                    definition.example?.let { example ->
                        Text(text = "Example: ${example}")
                    }
                    Spacer(modifier = Modifier.height(8.dp))

                }
                Spacer(modifier = Modifier.height(16.dp))


            }

            Text(
                text = "To know more:", fontWeight = FontWeight.Bold, color = Color.Black
            )
            wordInfo?.sourceUrl?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            top = 5.dp,
                            start = 10.dp,
                        )
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW)
                            intent.data = Uri.parse(wordInfo.sourceUrl)
                            context.startActivity(intent)
                        },
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.external_link),
                        contentDescription = null,
                        modifier = modifier.size(15.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = wordInfo.sourceUrl,
                        style = TextStyle(fontWeight = FontWeight.Bold),

                        )
                }
            }


        }

    }

}