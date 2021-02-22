package com.anubhav87.mvvmtutorial.di

import com.anubhav87.mvvmtutorial.adapter.NoteAdapter
import com.anubhav87.mvvmtutorial.db.NoteDatabase
import com.anubhav87.mvvmtutorial.repository.ArticleRepository
import com.anubhav87.mvvmtutorial.repository.EditNoteRepository
import com.anubhav87.mvvmtutorial.repository.NoteRepository
import com.anubhav87.mvvmtutorial.viewmodel.ArticleViewModel
import com.anubhav87.mvvmtutorial.viewmodel.EditNoteViewModel
import com.anubhav87.mvvmtutorial.viewmodel.NoteViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module


//Koin important keywords/functions
//=============================================
//startKoin : Create and register KoinApplication instance
//modules : Declare used modules
//androidContext : Use given Android context
//by inject() : allows to retrieve instances lazily
//get() : function to retrieve directly an instance (non lazy)
//koinComponent : For using koin features, tag the class with the same to get access to koin functions
//
//Koin Scopes
//===============================================
//single : creates an object that persistent with the entire container lifetime
//factory : creates new object each time. No persistence in container
//scoped: creates object that persist to associated scope lifetime

val dbModule = module {
    single { NoteDatabase.getInstance(
        context = get()
    )}
    factory { get<NoteDatabase>().noteDao() }
    factory { get<NoteDatabase>().articleDao() }
}

val repositoryModule = module {
    single { NoteRepository(get()) }
    single { EditNoteRepository(get())}
    single { ArticleRepository(get())}

}

val uiModule = module {
    factory { NoteAdapter() }
    //factory { MovieArticleAdapter(get(),get()) }

    viewModel { NoteViewModel(get()) }
    viewModel { EditNoteViewModel(get()) }
    viewModel { ArticleViewModel(get()) }

}


