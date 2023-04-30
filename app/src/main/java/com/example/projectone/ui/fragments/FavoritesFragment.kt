import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindls.databinding.FavoritesFragmentBinding
import com.example.projectone.data.viewModel.TickerViewModel
import com.example.projectone.db.model.TickerModelDB
import com.example.projectone.ui.adapter.TickerAdapter
import kotlinx.coroutines.launch

class FavoritesFragment : Fragment() {

    private var _binding: FavoritesFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var tickerViewModel: TickerViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FavoritesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tickerViewModel = ViewModelProvider(this)[TickerViewModel::class.java]

        initRv()

    }

    private fun initRv(){
        tickerViewModel.getFavoriteTickers().observe(viewLifecycleOwner) { favorites ->
            binding.apply {
                recyclerViewFavorites.layoutManager = LinearLayoutManager(requireContext())
                recyclerViewFavorites.setHasFixedSize(true)

                val adapter = TickerAdapter(favorites.toMutableList()) { tickerModel, isFavorite ->
                    onFavoriteClick(tickerModel, isFavorite)
                }
                recyclerViewFavorites.adapter = adapter
            }
        }
    }

    private fun onFavoriteClick(ticker: TickerModelDB, isFavorite: Boolean) {
        lifecycleScope.launch {
            tickerViewModel.updateFavoriteStatus(ticker.id, isFavorite)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

