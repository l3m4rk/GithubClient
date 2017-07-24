package example.l3m4rk.edu.githubclient.di.commits

import dagger.Module
import dagger.Provides
import example.l3m4rk.edu.githubclient.business.commits.CommitsInteractor
import example.l3m4rk.edu.githubclient.business.commits.ICommitsInteractor
import example.l3m4rk.edu.githubclient.business.commits.validation.ErrorParser
import example.l3m4rk.edu.githubclient.business.commits.validation.ServerCommitsParser
import example.l3m4rk.edu.githubclient.presentation.commits.presenter.CommitsPresenter
import example.l3m4rk.edu.githubclient.presentation.commits.presenter.ICommitsPresenter
import example.l3m4rk.edu.githubclient.services.GithubService

@Module
class CommitsModule {

    @Provides fun provideCommitsPresenter(commitsInteractor: ICommitsInteractor, errorParser: ErrorParser): ICommitsPresenter {
        return CommitsPresenter(commitsInteractor, errorParser)
    }

    @Provides fun provideCommitsInteractor(githubService: GithubService, serverCommitsParser: ServerCommitsParser): ICommitsInteractor {
        return CommitsInteractor(githubService, serverCommitsParser)
    }

    @Provides fun providesServerCommitsParser(): ServerCommitsParser {
        return ServerCommitsParser()
    }

}