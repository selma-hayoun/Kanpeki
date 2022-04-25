package com.dam.kanpeki.security.oauth2;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JdbcTokenStores extends JdbcTokenStore {

	public JdbcTokenStores(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public OAuth2AccessToken readAccessToken(String tokenValue) {
//		return super.readAccessToken(tokenValue);
		OAuth2AccessToken accessToken = null;

		try {
			accessToken = new DefaultOAuth2AccessToken(tokenValue);
		} catch (EmptyResultDataAccessException e) {
			if (log.isInfoEnabled()) {
				log.info("Failed to find access token for token " + tokenValue);
			}
		} catch (IllegalArgumentException e) {
			log.warn("Failed to deserialize access token for " + tokenValue, e);
			removeAccessToken(tokenValue);
		}

		return accessToken;
	}

}
