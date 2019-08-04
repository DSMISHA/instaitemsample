package com.chisw.instaitemsample.base

import com.chisw.instaitemsample.repository.PostsRepository

interface Injection {
    fun injectPostsRepository(): PostsRepository
}