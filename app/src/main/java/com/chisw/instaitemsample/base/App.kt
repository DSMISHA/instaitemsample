package com.chisw.instaitemsample.base

import android.app.Application
import com.chisw.instaitemsample.repository.PostsRepository
import com.chisw.instaitemsample.repository.PostsRepositoryImpl
import com.google.gson.Gson

class App: Application(), Injection {

    private val postsRepository: PostsRepository by lazy { PostsRepositoryImpl(Gson(),this) }

    override fun injectPostsRepository(): PostsRepository {
        return postsRepository
    }
}