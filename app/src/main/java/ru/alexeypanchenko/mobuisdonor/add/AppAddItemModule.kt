package ru.alexeypanchenko.mobuisdonor.add

import dagger.Module
import dagger.Provides
import ru.alexeypanchenko.mobuisdonor.ItemsRepository
import ru.alexeypanchenko.mobuisdonor.add.dependencies.AddItemRepository
import ru.alexeypanchenko.mobuisdonor.db.DatabaseModule
import ru.alexeypanchenko.mobuisdonor.db.ItemEntry

@Module
class AppAddItemModule {

    @Provides
    fun getAddItemRepository(itemsRepository: ItemsRepository): AddItemRepository {
        return object : AddItemRepository {
            override suspend fun addItem(item: AddItem) {
                itemsRepository.addItem(
                    ItemEntry(
                        title = item.title,
                        description = item.description,
                        additionalText = item.additionalText
                    )
                )
            }
        }
    }
}