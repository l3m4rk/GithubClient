package example.l3m4rk.edu.githubclient.di

import dagger.Module
import dagger.Provides
import example.l3m4rk.edu.githubclient.di.login.LoginComponent
import example.l3m4rk.edu.githubclient.di.user.UserComponent
import example.l3m4rk.edu.githubclient.services.GithubService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module(subcomponents = arrayOf(LoginComponent::class, UserComponent::class))
class NetworkModule {

    companion object {
        private const val BASE_URL = "https://api.github.com/"
        private const val AUTH_HEADER = "Authorization"
        private const val READ_TIMEOUT = 12_000L
        private const val CONNECT_TIMEOUT = 12_000L
    }

    @Provides fun provideGithubService(okHttpClient: OkHttpClient?): GithubService {
        val builder = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)

        return builder.build().create(GithubService::class.java)
    }

    @Provides fun provideOkHttpClient(token: String): OkHttpClient? {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                .addInterceptor { chain ->
                    val r = chain.request()
                            .newBuilder()
                            .addHeader(AUTH_HEADER, token)
                            .build()
                    chain.proceed(r)
                }
                .build()
    }

}