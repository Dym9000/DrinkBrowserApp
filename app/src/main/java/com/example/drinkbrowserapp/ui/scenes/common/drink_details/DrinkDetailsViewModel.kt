package com.example.drinkbrowserapp.ui.scenes.common.drink_details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.drinkbrowserapp.domain.DrinkDomain
import com.example.drinkbrowserapp.ui.common.repository.DrinkRepository
import com.example.drinkbrowserapp.util.Constants
import kotlinx.coroutines.launch

class DrinkDetailsViewModel(private val drinkId: Int, private val drinkRepository: DrinkRepository,
private val fragment: Int) : ViewModel() {

    val drinkDetailsList = drinkRepository.getDrinkDetails(Constants.API_TOKEN_KEY, drinkId, fragment)

    private val _drinkDetails = MutableLiveData<DrinkDomain>()
    val drinkDetails: LiveData<DrinkDomain>
        get() {
            return _drinkDetails
        }

    private val _isFavourite = MutableLiveData<Int>()
    val isFavourite: LiveData<Int>
    get(){
        return _isFavourite
    }

    init{
        viewModelScope.launch{
            _isFavourite.value = drinkRepository.isDrinkInFavourites(drinkId)
        }
    }

    private val _isFavouriteToastMessage = MutableLiveData<Boolean?>()
    val isFavouriteToastMessage: LiveData<Boolean?>
        get(){
            return _isFavouriteToastMessage
        }

    fun onFabClicked(){
        drinkRepository.addOrRemoveFromFavList(drinkId)
        setIsFavourite()
    }

    private fun setIsFavourite(){
        if(_isFavourite.value == 1){
            _isFavourite.value = 0
            showIsFavouriteMessage(false)
        }else{
            _isFavourite.value = 1
            showIsFavouriteMessage(true)
        }
    }

    fun onDataFetched(data: DrinkDomain) {
        _drinkDetails.value = data
    }

    private fun showIsFavouriteMessage(addedToFavourites: Boolean){
        _isFavouriteToastMessage.value = addedToFavourites
    }

    fun messageShown(){
        _isFavouriteToastMessage.value = null
    }

}