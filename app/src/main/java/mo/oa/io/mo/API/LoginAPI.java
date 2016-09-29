package mo.oa.io.mo.API;

import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by max-code on 2016/9/19.
 */
public interface LoginAPI {
    @POST("")
    Observable<String> simpleLogin(@Query("loginType") String logintype,@Query("username") String username,@Query("password") String password);
    @POST("callService.do?URL=/mobile/service/ignoreValidateUser.do?reqCode=userEasyLogin")
    Observable<String> loginByCheckCode(@Query("loginType")String logintype,@Query("authCode") String CodeNum,@Query("username") String username);
    @POST("")
    Observable<String> sendCheckCode(@Query("username")String username);
}
