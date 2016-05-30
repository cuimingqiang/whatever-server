package com.cmq.whatever.uc.services;

import com.cmq.whatever.uc.https.params.RegisterParam;
import com.cmq.whatever.uc.https.results.BaseResult;

/**
 * Created by cuimingqiang on 16/5/30.
 */

public interface UserService {
    BaseResult register(RegisterParam param);
}
