package com.chisw.instaitemsample.adapter

import android.os.Build
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.chisw.instaitemsample.R
import com.chisw.instaitemsample.common.loadCircleImage
import com.chisw.instaitemsample.common.visibleIf
import com.chisw.instaitemsample.model.Post
import com.chisw.instaitemsample.model.PostWrapper
import kotlinx.android.synthetic.main.item_post.view.*


class PostsRecyclerAdapter : RecyclerView.Adapter<PostsRecyclerAdapter.PostViewHolder>() {

    private var data : List<PostWrapper>? = null

    private var listener: OnPostClickListener? = null

    fun setData(items: List<PostWrapper>?){
        data = items
        notifyDataSetChanged()
    }

    fun setClickListener(listener: OnPostClickListener?){
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
       return PostViewHolder(view)
    }

    override fun getItemCount() = data?.size ?: 0

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        data?.get(position)?.let { holder.bind(it) }
    }

    inner class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        fun bind(binding: PostWrapper){
            itemView.ivAvatar.loadCircleImage(binding.post.owner.avatar)
            itemView.tvName.text = binding.post.owner.fullName
            itemView.tvLocation.text = binding.post.location
            setupRecycler(binding)
            setPhotoCounter(binding)
            setComment(binding)
            setListeners()
        }

        private fun setListeners(){
            itemView.ivLike.setOnClickListener {
                data?.get(adapterPosition)?.let { listener?.onPostLikeClicked(it.post) }
            }
            itemView.ivComment.setOnClickListener {
                data?.get(adapterPosition)?.let { listener?.onPostCommentClicked(it.post) }
            }
            itemView.ivSend.setOnClickListener {
                data?.get(adapterPosition)?.let { listener?.onPostSendClicked(it.post) }
            }
            itemView.ivBookmark.setOnClickListener {
                data?.get(adapterPosition)?.let { listener?.onPostBookmarkClicked(it.post) }
            }
            itemView.ivAvatar.setOnClickListener {
                data?.get(adapterPosition)?.let { listener?.onPostUserClicked(it.post) }
            }
            itemView.ivMore.setOnClickListener {
                data?.get(adapterPosition)?.let { listener?.onPostMoreClicked(it.post) }
            }
            itemView.tvComment.setOnClickListener {
                data?.get(adapterPosition)?.let { listener?.onPostCommentTextClicked(it.post) }
            }
        }

        private fun setupRecycler(binding: PostWrapper){
            val adapter = ImagesRecyclerAdapter()
            itemView.recyclerPhoto.adapter = adapter
            itemView.recyclerPhoto.layoutManager = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, false)
            val snapHelper = PagerSnapHelper()
            itemView.recyclerPhoto.attachSnapHelperWithListener(snapHelper,
                SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL,object : OnSnapPositionChangeListener{
                    override fun onSnapPositionChange(position: Int) {
                        data?.get(adapterPosition)?.let {
                            it.currentItem = position
                            moveIndicator(it)
                            setPhotoCounter(binding)
                        }
                    }
                })
            adapter.setData(binding.post.photos)
            itemView.recyclerPhoto.scrollToPosition(binding.currentItem)
            moveIndicator(binding)
        }
        private fun moveIndicator(binding: PostWrapper){
            itemView.indicator?.apply {
                count = binding.post.photos.size
                this.visibleIf { count > 1 }
                selection = binding.currentItem
            }
        }

        private fun setPhotoCounter(binding: PostWrapper){
            itemView.cntContainer.visibleIf { binding.post.photos.size > 1 }
            val cnt = (binding.currentItem + 1).toString() + "/" + binding.post.photos.size
            itemView.tvCounter.text = cnt
        }

        private fun setComment(binding: PostWrapper){
            val userName = binding.post.comments[0].userName
            val comment = binding.post.comments[0].text
            val text = "$userName $comment"
            val sBuilder = SpannableStringBuilder(text)
            sBuilder.setSpan(android.text.style.StyleSpan(android.graphics.Typeface.BOLD),
                    0, userName.length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

            val color = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                itemView.context.getColor(R.color.colorBlack)
            } else {
                itemView.context.resources.getColor(R.color.colorBlack)
            }
            val colorSpan = ForegroundColorSpan(color)
            sBuilder.setSpan(colorSpan, 0, userName.length, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)

            itemView.tvComment.text = sBuilder
        }
    }

    interface OnPostClickListener{
        fun onPostLikeClicked(post: Post)
        fun onPostCommentClicked(post: Post)
        fun onPostSendClicked(post: Post)
        fun onPostBookmarkClicked(post: Post)
        fun onPostUserClicked(post: Post)
        fun onPostMoreClicked(post: Post)
        fun onPostCommentTextClicked(post: Post)
    }
}