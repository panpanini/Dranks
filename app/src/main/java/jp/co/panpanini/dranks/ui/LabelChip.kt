package jp.co.panpanini.dranks.ui

import androidx.compose.foundation.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.ui.tooling.preview.Preview

@Composable
fun LabelChip(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        modifier = modifier.background(MaterialTheme.colors.primary, RoundedCornerShape(16.dp))
            .padding(8.dp),
        color = MaterialTheme.colors.onPrimary
    )
}

@Preview
@Composable
fun DarkLabelChipPreview() {
    DranksTheme(darkTheme = true){
        LabelChip("Dark Label Chip", modifier = Modifier.padding(8.dp))
    }
}

@Preview
@Composable
fun LightLabelChipPreview() {
    DranksTheme(darkTheme = false) {
        LabelChip("Light Label Chip", modifier = Modifier.padding(8.dp))
    }
}