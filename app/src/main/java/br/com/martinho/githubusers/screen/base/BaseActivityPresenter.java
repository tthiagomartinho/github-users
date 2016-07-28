package br.com.martinho.githubusers.screen.base;

import javax.inject.Inject;

import br.com.martinho.githubusers.network.API;

/**
 * Created by Thiago Martinho on 27/07/2016.
 */
public abstract class BaseActivityPresenter {

    @Inject
    protected API api;
}
