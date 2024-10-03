package com.lessthanthree.galistapp.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import com.lessthanthree.galistapp.favorite.di.FavoriteModule.favoriteModule
import com.lessthanthree.galistapp.favorite.ui.FavoriteFragment
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FavoriteFragment())
                .commitNow()
        }

        loadKoinModules(favoriteModule)

        supportActionBar?.title =
            Html.fromHtml("<font color='#ffffff'>Game Favorite</font>", Html.FROM_HTML_MODE_COMPACT)
    }
}