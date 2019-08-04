package com.chisw.instaitemsample.screens

import com.chisw.instaitemsample.model.PostWrapper
import com.chisw.instaitemsample.repository.PostsRepository


class MainPresenter(private val repository: PostsRepository) : MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun loadData() {
        view?.showData(repository.loadPosts().map { PostWrapper(it) })
    }

    override fun attachView(view: MainContract.View) {
        this.view = view
    }

    override fun detachView() {
        this.view = null
    }
}