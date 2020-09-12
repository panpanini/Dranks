package jp.co.panpanini.dranks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.platform.setContent
import jp.co.panpanini.dranks.detail.flux.DetailActionCreator
import jp.co.panpanini.dranks.detail.flux.DetailFluxProvider
import jp.co.panpanini.dranks.detail.flux.DetailStore
import jp.co.panpanini.dranks.ui.DranksTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {

    private val fluxProvider: DetailFluxProvider by viewModel()

    private val detailActionCreator: DetailActionCreator
        get() = fluxProvider.actionCreator

    private val detailStore: DetailStore
        get() = fluxProvider.store

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DranksTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    Column {
                        DrinksAppBar()
                    }
                }
            }
        }
    }


}