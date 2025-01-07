package com.worldofnuclear.wonapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.worldofnuclear.wonapp.ui.theme.WoNAppTheme

@Composable
fun WorldOfNuclearApp(
    fluxViewModel: FluxViewModel = viewModel(factory = FluxViewModel.Factory),
) {
    // TODO: top-level structure and navigation go here
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        FluxMainScreen(
            viewModel = fluxViewModel,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorldOfNuclearApp() {
    WoNAppTheme {
        WorldOfNuclearApp()
    }
}
