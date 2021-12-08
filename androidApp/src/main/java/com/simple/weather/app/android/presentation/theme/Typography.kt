package com.simple.weather.app.android.presentation.theme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
private fun TypographyPreview() {
    Column(Modifier.width(400.dp).padding(10.dp).background(Color.White)) {

        Text(text = "H1 " ,style = MaterialTheme.typography.h1)

        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth().background(Color.Black))

        Text(text = "H2 " ,style = MaterialTheme.typography.h2)

        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth().background(Color.Black))

        Text(text = "H3 " ,style = MaterialTheme.typography.h3)

        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth().background(Color.Black))

        Text(text = "H4 " ,style = MaterialTheme.typography.h4)

        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth().background(Color.Black))

        Text(text = "H5 " ,style = MaterialTheme.typography.h5)

        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth().background(Color.Black))

        Text(text = "H6 " ,style = MaterialTheme.typography.h6)

        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth().background(Color.Black))

        Text(text = "Subtitle 1" ,style = MaterialTheme.typography.subtitle1)

        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth().background(Color.Black))

        Text(text = "Subtitle 2" ,style = MaterialTheme.typography.subtitle2)

        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth().background(Color.Black))

        Text(text = "Body 1" ,style = MaterialTheme.typography.body1)

        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth().background(Color.Black))

        Text(text = "Body 2" ,style = MaterialTheme.typography.body2)

        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth().background(Color.Black))

        Text(text = "Button" ,style = MaterialTheme.typography.button)

        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth().background(Color.Black))

        Text(text = "Caption" ,style = MaterialTheme.typography.caption)

        Spacer(modifier = Modifier.height(4.dp).fillMaxWidth().background(Color.Black))

        Text(text = "Overline" ,style = MaterialTheme.typography.overline)
    }
}