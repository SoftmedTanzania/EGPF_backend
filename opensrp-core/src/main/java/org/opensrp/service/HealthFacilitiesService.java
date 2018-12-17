package org.opensrp.service;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.opensrp.domain.HealthFacilities;
import org.opensrp.repository.HealthFacilityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class HealthFacilitiesService {

	private static Logger logger = LoggerFactory.getLogger(HealthFacilitiesService.class.toString());

	private HttpClient client;

	@Autowired
	private HealthFacilityRepository healthFacilityRepository;


	public HealthFacilitiesService() {
		this.client = HttpClientBuilder.create().build();
	}


	public void storeHealthFacilities(HealthFacilities healthFacilities) throws SQLException {
		// create jdbc template to persist the ids
		try {
			if (!this.checkIfFacilyExists(healthFacilities)) {
				healthFacilityRepository.save(healthFacilities);
				logger.info("Successfully saved client " + healthFacilities.getFacilityName());
			}
		}
		catch (Exception e) {
			logger.error("", e);
		}
	}

	public Boolean checkIfFacilyExists(HealthFacilities healthFacilities) throws SQLException {
		try {
			String checkIfExistQuery = "SELECT count(*) from " + HealthFacilities.tbName + " WHERE " + HealthFacilities.COL_OPENMRS_UIID +" = ?";
			String[] args = new String[1];
			args[0] = (String) healthFacilities.getOpenMRSUIID();

			int rowCount = healthFacilityRepository.checkIfExists(checkIfExistQuery, args);

			logger.info(
					"[checkIfClientExists] - Card Number:" + args[0] + " - [Exists] " + (rowCount == 0 ? "false" : "true"));

			return rowCount >= 1;
		}
		catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}
}
