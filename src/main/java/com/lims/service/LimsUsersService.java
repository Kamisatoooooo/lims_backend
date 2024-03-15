package com.lims.service;

import com.lims.entity.LimsUsers;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lims.form.LoginForm;
import com.lims.form.RegistForm;
import com.lims.form.SearchForm;
import com.lims.vo.DataFormat;
import com.lims.vo.PageVO;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author duhaoran
 * @since 2023-05-01
 */
public interface LimsUsersService extends IService<LimsUsers> {
    public DataFormat login(LoginForm loginForm);

    public DataFormat regist(RegistForm registForm);

    public PageVO list(Integer page, Integer size);

    public PageVO search(SearchForm searchForm);

    public PageVO newRegists(Integer page, Integer size);

    public DataFormat changeInfo(LimsUsers limsUsers);
}
