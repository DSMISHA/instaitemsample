package com.chisw.instaitemsample.screens

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.chisw.instaitemsample.R
import com.chisw.instaitemsample.adapter.PostsRecyclerAdapter
import com.chisw.instaitemsample.base.BaseActivity
import com.chisw.instaitemsample.base.Injection
import com.chisw.instaitemsample.common.decoration.MarginItemDecorator
import com.chisw.instaitemsample.model.Post
import com.chisw.instaitemsample.model.PostWrapper
import com.chisw.instaitemsample.repository.PostsRepository
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), MainContract.View, PostsRecyclerAdapter.OnPostClickListener {

    private val repository: PostsRepository by lazy { (applicationContext as Injection).injectPostsRepository() }

    private val adapter : PostsRecyclerAdapter by lazy { PostsRecyclerAdapter() }

    private val presenter: MainContract.Presenter by lazy { MainPresenter(repository) }

    override fun showData(data: List<PostWrapper>) {
        adapter.setData(data)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val margin = resources.getDimensionPixelSize(R.dimen.default_recycler_margin)
        adapter.setClickListener(this)
        postsRecycler?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        postsRecycler?.addItemDecoration(MarginItemDecorator(top = margin, bottom = margin))
        postsRecycler.adapter = adapter
        presenter.attachView(this)
        presenter.loadData()
    }


    override fun onPostLikeClicked(post: Post) {
        showToast("on like clicked, post owner = ${post.owner}")
    }

    override fun onPostCommentClicked(post: Post) {
        showToast("on comment clicked, post owner = ${post.owner}")
    }

    override fun onPostSendClicked(post: Post) {
        showToast("on send clicked, post owner = ${post.owner}")
    }

    override fun onPostBookmarkClicked(post: Post) {
        showToast("on bookmark clicked, post owner = ${post.owner}")
    }

    override fun onPostUserClicked(post: Post) {
        showToast("on user clicked, post owner = ${post.owner}")
    }

    override fun onPostMoreClicked(post: Post) {
        showToast("on more clicked, post owner = ${post.owner}")
    }

    override fun onPostCommentTextClicked(post: Post) {
        showToast("on comment text clicked, post owner = ${post.owner}")
    }

    override fun onDestroy() {
        presenter.detachView()
        super.onDestroy()
    }
}
