package br.com.martinho.githubusers.ui.base;

import javax.inject.Inject;

import br.com.martinho.githubusers.data.service.API;

/**
 * Created by Thiago Martinho on 27/07/2016.
 */
public abstract class BaseActivityPresenter {

    @Inject
    protected API api;
}
