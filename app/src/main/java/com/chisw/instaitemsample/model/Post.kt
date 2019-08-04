package com.chisw.instaitemsample.model



data class Post(
    val id:String,
    val owner: User,
    val location: String,
    val likesCount: Int,
    val photos: List<String>,
    val comments: List<Comment>)