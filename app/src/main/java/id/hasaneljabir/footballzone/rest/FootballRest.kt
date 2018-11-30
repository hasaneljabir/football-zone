package id.hasaneljabir.footballzone.rest

import id.hasaneljabir.footballzone.entity.SearchedEvent
import id.hasaneljabir.footballzone.entity.event.EventResponse
import id.hasaneljabir.footballzone.entity.player.PlayerDetailResponse
import id.hasaneljabir.footballzone.entity.player.PlayerResponse
import id.hasaneljabir.footballzone.entity.team.TeamResponse
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballRest {

    @GET("eventspastleague.php")
    fun getLastmatch(@Query("id") id: String): Flowable<EventResponse>

    @GET("eventsnextleague.php")
    fun getUpcomingMatch(@Query("id") id: String): Flowable<EventResponse>

    @GET("searchevents.php")
    fun searchMatches(@Query("e") query: String?): Flowable<SearchedEvent>

    @GET("lookupteam.php")
    fun getTeam(@Query("id") id: String): Flowable<TeamResponse>

    @GET("searchteams.php")
    fun getTeamBySearch(@Query("t") query: String): Flowable<TeamResponse>

    @GET("lookup_all_teams.php")
    fun getAllTeam(@Query("id") id: String): Flowable<TeamResponse>

    @GET("lookupevent.php")
    fun getEventById(@Query("id") id: String): Flowable<EventResponse>

    @GET("lookup_all_players.php")
    fun getAllPlayers(@Query("id") id: String?): Flowable<PlayerResponse>

    @GET("lookupplayer.php")
    fun getPlayerDetail(@Query("id") id: String?): Flowable<PlayerDetailResponse>
}