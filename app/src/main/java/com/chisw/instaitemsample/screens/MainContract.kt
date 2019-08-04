package com.chisw.instaitemsample.screens

import com.chisw.instaitemsample.model.PostWrapper


interface MainContract{
    interface View{
        fun showData(data: List<PostWrapper>)
    }

    interface Presenter{
        fun loadData()
        fun attachView(view: View)
        fun detachView()
    }
}