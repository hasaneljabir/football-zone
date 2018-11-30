package id.hasaneljabir.footballzone.feature.team

import id.hasaneljabir.footballzone.entity.repository.TeamRepositoryImpl
import id.hasaneljabir.footballzone.entity.team.TeamResponse
import id.hasaneljabir.footballzone.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.*

class TeamsPresenter(
    val mView: TeamsContract.View, val teamRepositoryImpl: TeamRepositoryImpl,
    val scheduler: SchedulerProvider
) : TeamsContract.Presenter {

    override fun searchTeam(teamName: String) {
//        mView.showLoading()
        compositeDisposable.add(
            teamRepositoryImpl.getTeamBySearch(teamName)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<TeamResponse>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: TeamResponse) {
                        mView.displayTeams(t.teams ?: Collections.emptyList())
                    }

                    override fun onError(t: Throwable?) {
                        mView.displayTeams(Collections.emptyList())
                        mView.hideLoading()
                    }

                })
        )
    }

    val compositeDisposable = CompositeDisposable()
    override fun getTeamData(leagueName: String) {
        mView.showLoading()
        compositeDisposable.add(
            teamRepositoryImpl.getAllTeam(leagueName)
                .observeOn(scheduler.ui())
                .subscribeOn(scheduler.io())
                .subscribeWith(object : ResourceSubscriber<TeamResponse>() {
                    override fun onComplete() {
                        mView.hideLoading()
                    }

                    override fun onNext(t: TeamResponse) {
                        mView.displayTeams(t.teams ?: Collections.emptyList())
                    }

                    override fun onError(t: Throwable?) {
                        mView.displayTeams(Collections.emptyList())
                        mView.hideLoading()
                    }

                })
        )
    }

    override fun onDestroy() {
        compositeDisposable.dispose()
    }

}