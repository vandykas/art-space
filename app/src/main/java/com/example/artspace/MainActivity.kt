package com.example.artspace

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Scaffold(
                    topBar = { TopBar() }
                ) { innerPadding ->
                    Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                        ArtSpaceApp()
                    }
                }
            }
        }
    }
}

@Composable
fun TopBar() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .statusBarsPadding()
            .fillMaxWidth()
            .height(40.dp)
    ) {
        Text(
            text = "Art Space",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun ArtSpaceApp() {
    val artCollection = listOf(
        Art(
            R.drawable.petra_jordan,
            R.string.petra,
            R.string.jordan
        ),
        Art(
            R.drawable.christ_the_redeemer_brazil,
            R.string.christ_the_redeemer,
            R.string.brazil
        ),
        Art(
            R.drawable.coloseum_italy,
            R.string.coloseum,
            R.string.italy
        ),
        Art(
            R.drawable.taj_mahal_india,
            R.string.taj_mahal,
            R.string.india
        ),
        Art(
            R.drawable.machu_picchu_peru,
            R.string.machu_picchu,
            R.string.peru
        ),
        Art(
            R.drawable.chichen_itza_mexico,
            R.string.chichen_itza,
            R.string.mexico
        ),
        Art(
            R.drawable.great_wall_of_china,
            R.string.great_wall_of_china,
            R.string.china
        ),
    )

    var currentIndex by remember { mutableStateOf(0) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ArtWithControl(
                art = artCollection[currentIndex].art,
                previousAction = {
                    val collectionSize = artCollection.size
                    currentIndex = (currentIndex - 1 + collectionSize) % collectionSize
                },
                nextAction = {
                    val collectionSize = artCollection.size
                    currentIndex = (currentIndex + 1) % collectionSize
                },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(32.dp))
            DescriptionCard(
                description = artCollection[currentIndex].description,
                additionalInformation = artCollection[currentIndex].additionalInformation
            )
        }
    }
}

@Composable
fun ArtWithControl(
    @DrawableRes art: Int,
    previousAction: () -> Unit,
    nextAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        Button(
            onClick = previousAction,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBackIosNew,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
        }
        Image(
            painter = painterResource(art),
            contentDescription = stringResource(R.string.petra),
            modifier = Modifier.width(280.dp).height(500.dp)
        )
        Button(
            onClick = nextAction,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(0.dp),
            modifier = Modifier.size(36.dp)
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForwardIos,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

        }
    }
}

@Composable
fun DescriptionCard(
    @StringRes description: Int,
    @StringRes additionalInformation: Int,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(description),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(additionalInformation),
            textAlign = TextAlign.Center
        )
    }
}

@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceTheme {
            Scaffold(
                topBar = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                    ) {
                        Text(
                            text = "Art Space",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            ) { innerPadding ->
                Surface(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
                    ArtSpaceApp()
                }
            }
        }
    }
}