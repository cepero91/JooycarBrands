package com.lumos.jooycarbrands.ui.route.graph

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lumos.jooycarbrands.ui.brand.BrandScreen
import com.lumos.jooycarbrands.ui.brand.ModelsBrandScreen
import com.lumos.jooycarbrands.ui.brand.entities.BrandArgs
import com.lumos.jooycarbrands.ui.brand.navtype.BrandArgType
import com.lumos.jooycarbrands.ui.route.BrandsRoute
import com.lumos.jooycarbrands.ui.route.BrandsRoute.Companion.BRAND_ARGS
import com.lumos.jooycarbrands.ui.route.action.BrandsAction
import kotlinx.coroutines.InternalCoroutinesApi

/**
 * Created by Cepero on 30/04/2022.
 * jose.cepero@reign.cl
 *
 * La Habana, Cuba.
 */
@ExperimentalFoundationApi
@ExperimentalMaterialApi
@InternalCoroutinesApi
@ExperimentalAnimationApi
@Composable
fun JooycarBrandsGraph(
    navController: NavHostController = rememberNavController()
) {
    val scaffoldState = rememberScaffoldState()
    val brandsAction = remember(navController) { BrandsAction(navController) }

    NavHost(navController = navController, startDestination = BrandsRoute.Brands.path) {

        composable(BrandsRoute.Brands.path) {
            BrandScreen(scaffoldState, navigateToModels = brandsAction.navigateToModels)
        }

        composable(
            BrandsRoute.Models.path,
            arguments = listOf(navArgument(BRAND_ARGS) { type = BrandArgType() })
        ) { backStackEntry ->
            val args = backStackEntry.arguments?.getParcelable<BrandArgs>(BRAND_ARGS)
            ModelsBrandScreen(
                scaffoldState,
                navigateUp = brandsAction.navigateUp,
                brandsSelected = args
            )
        }
    }
}