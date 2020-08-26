package com.anubhav87.mvvmtutorial.di

import com.anubhav87.mvvmtutorial.adapter.MovieArticleAdapter
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

val dbModule = module {
    single { NoteDatabase.getInstance(
        context = get()
    )}
    factory { get<NoteDatabase>().noteDao() }
}

val repositoryModule = module {
    single { NoteRepository(get()) }
    single { EditNoteRepository(get())}
    single { ArticleRepository()}

}

val uiModule = module {
    factory { NoteAdapter() }
    //factory { MovieArticleAdapter(get(),get()) }

    viewModel { NoteViewModel(get()) }
    viewModel { EditNoteViewModel(get()) }
    viewModel { ArticleViewModel(get()) }

}


