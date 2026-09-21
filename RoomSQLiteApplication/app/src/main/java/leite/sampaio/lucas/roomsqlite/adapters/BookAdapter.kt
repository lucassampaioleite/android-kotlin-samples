package leite.sampaio.lucas.roomsqlite.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import leite.sampaio.lucas.roomsqlite.databinding.BookItemBinding
import leite.sampaio.lucas.roomsqlite.entities.Book

class BookAdapter(
    private val onItemClick: (Book) -> Unit,
    private val onDeleteClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private var books = emptyList<Book>()

    class BookViewHolder(private val binding: BookItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book, onItemClick: (Book) -> Unit, onDeleteClick: (Book) -> Unit) {
            binding.tvTitle.text = book.title
            binding.tvAuthor.text = "Autor: ${book.author}"
            binding.tvYear.text = "Ano: ${book.year}"

            binding.root.setOnClickListener {
                onItemClick(book)
            }

            binding.btnDelete.setOnClickListener {
                onDeleteClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = BookItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position], onItemClick, onDeleteClick)
    }

    override fun getItemCount(): Int {
        return books.size
    }

    fun updateList(novosLivros: List<Book>) {
        books = novosLivros
        notifyDataSetChanged()
    }
}

