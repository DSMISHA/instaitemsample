package com.chisw.instaitemsample.repository

import android.content.Context
import com.chisw.instaitemsample.model.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException


interface PostsRepository {
    fun loadPosts(): List<Post>
}

class PostsRepositoryImpl(private val gson: Gson, private val context: Context) : PostsRepository {

    override fun loadPosts(): List<Post> {
        val posts = loadJSONFromAsset()
        return gson.fromJson(posts, object : TypeToken<List<Post>>() {}.type)
    }


    private fun loadJSONFromAsset(): String? {
        val json: String
        try {
            val inputStream = context.assets.open("posts.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, java.nio.charset.StandardCharsets.UTF_8)
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }

        return json
    }
}