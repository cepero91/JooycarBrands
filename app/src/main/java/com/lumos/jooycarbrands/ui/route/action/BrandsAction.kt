package com.lumos.jooycarbrands.ui.route.action

import android.net.Uri
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.lumos.jooycarbrands.ui.brand.entities.BrandArgs
import com.lumos.jooycarbrands.ui.route.BrandsRoute

class BrandsAction(navController: NavHostController) {

    val navigateToModels: (args: BrandArgs) -> Unit = {
        val json = Uri.encode(Gson().toJson(it))
        navController.navigate(BrandsRoute.Models.path.replace("{$BRAND_ARGS}", json))
    }

    val navigateUp: () -> Unit = {
        navController.navigateUp()
    }

    companion object {
        const val BRAND_ARGS = "args"
    }
}
