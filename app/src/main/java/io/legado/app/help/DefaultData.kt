package io.legado.app.help

import android.os.Build
import io.legado.app.data.appDb
import io.legado.app.data.entities.*
import io.legado.app.help.config.ReadBookConfig
import io.legado.app.help.config.ThemeConfig
import io.legado.app.model.BookCover
import io.legado.app.utils.GSON
import io.legado.app.utils.fromJsonArray
import io.legado.app.utils.fromJsonObject
import splitties.init.appCtx
import java.io.File
import java.util.*

object DefaultData {

    val httpTTS: List<HttpTTS> by lazy {
        val json =
            String(
                appCtx.assets.open("defaultData${File.separator}httpTTS.json")
                    .readBytes()
            )
        HttpTTS.fromJsonArray(json).getOrElse {
            emptyList()
        }
    }

    val readConfigs: List<ReadBookConfig.Config> by lazy {
        val json = String(
            appCtx.assets.open("defaultData${File.separator}${ReadBookConfig.configFileName}")
                .readBytes()
        )
        GSON.fromJsonArray<ReadBookConfig.Config>(json).getOrNull()
            ?: emptyList()
    }

    val txtTocRules: List<TxtTocRule> by lazy {
        val json = String(
            appCtx.assets.open("defaultData${File.separator}txtTocRule.json")
                .readBytes()
        )
        GSON.fromJsonArray<TxtTocRule>(json).getOrNull() ?: emptyList()
    }

    val themeConfigs: List<ThemeConfig.Config> by lazy {
        val json = String(
            appCtx.assets.open("defaultData${File.separator}${ThemeConfig.configFileName}")
                .readBytes()
        )
        GSON.fromJsonArray<ThemeConfig.Config>(json).getOrNull() ?: emptyList()
    }

    val rssSources: List<RssSource> by lazy {
        val json = String(
            appCtx.assets.open("defaultData${File.separator}rssSources.json")
                .readBytes()
        )
        RssSource.fromJsonArray(json).getOrDefault(emptyList())
    }

    val bookSources: Result<MutableList<BookSource>> by lazy {
        val locale:Locale =  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            appCtx.resources.configuration.locales[0]
        } else appCtx.resources.configuration.locale
        val fileName:String = if (Locale.CHINA.equals(locale)) "bookSources_zh.json" else "bookSources.json"
        val json = String(
            appCtx.assets.open("defaultData${File.separator}${fileName}")
                .readBytes()
        )
        BookSource.fromJsonArray(json)
    }

    val coverRuleConfig: BookCover.CoverRuleConfig by lazy {
        val json = String(
            appCtx.assets.open("defaultData${File.separator}coverRuleConfig.json")
                .readBytes()
        )
        GSON.fromJsonObject<BookCover.CoverRuleConfig>(json).getOrThrow()!!
    }

    val keyboardAssists: List<KeyboardAssist> by lazy {
        val json = String(
            appCtx.assets.open("defaultData${File.separator}keyboardAssists.json")
                .readBytes()
        )
        GSON.fromJsonArray<KeyboardAssist>(json).getOrNull()!!
    }

    fun importDefaultHttpTTS() {
        appDb.httpTTSDao.deleteDefault()
        appDb.httpTTSDao.insert(*httpTTS.toTypedArray())
    }

    fun importDefaultTocRules() {
        appDb.txtTocRuleDao.deleteDefault()
        appDb.txtTocRuleDao.insert(*txtTocRules.toTypedArray())
    }

    fun importDefaultRssSources() {
        appDb.rssSourceDao.insert(*rssSources.toTypedArray())
    }

    fun importDefaultBookSources() {
        if(bookSources.isSuccess){
           bookSources.getOrDefault(mutableListOf()).forEach {
               if (appDb.bookSourceDao.getBookSource(it.bookSourceUrl) != null ) {
                   appDb.bookSourceDao.update(it)
               } else {
                   appDb.bookSourceDao.insert(it)
               }
           }
        }

    }

}