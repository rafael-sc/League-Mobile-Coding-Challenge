package life.league.challenge.kotlin.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import life.league.challenge.kotlin.databinding.PostItemLayoutBinding
import life.league.challenge.kotlin.domain.model.Post

class PostsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var items = listOf<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        PostViewHolder(
            PostItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        (holder as? PostViewHolder)?.onBind(item)
    }

    override fun getItemCount(): Int = items.size

    class PostViewHolder(
        private val binding: PostItemLayoutBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: Post) {
            binding.textViewPostAuthorName.text = item.user.name
            binding.textViewPostContent.text = item.content
            binding.textViewPostTitle.text = item.title
        }
    }

    fun setItems(list: List<Post>) {
        this.items = list
    }
}
