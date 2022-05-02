package com.lumos.jooycarbrands.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Surface
import com.lumos.jooycarbrands.ui.route.graph.JooycarBrandsGraph
import com.lumos.jooycarbrands.ui.theme.JooycarBrandsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalFoundationApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@InternalCoroutinesApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JooycarBrandsTheme {
                // A surface container using the 'background' color from the theme
                Surface {
                    JooycarBrandsGraph()
                }
            }
        }
    }
}