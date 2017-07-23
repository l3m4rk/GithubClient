package example.l3m4rk.edu.githubclient.di.commits

import dagger.Module
import dagger.Provides
import example.l3m4rk.edu.githubclient.presentation.commits.presenter.CommitsPresenter
import example.l3m4rk.edu.githubclient.presentation.commits.presenter.ICommitsPresenter
import example.l3m4rk.edu.githubclient.services.GithubService

@Module
class CommitsModule {

    @Provides fun provideCommitsPresenter(githubService: GithubService): ICommitsPresenter {
        return CommitsPresenter(githubService)
    }

}