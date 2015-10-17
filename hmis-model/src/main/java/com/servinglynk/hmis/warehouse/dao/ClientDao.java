package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.model.live.Client;

public interface ClientDao extends ParentDao {
	public void hydrateLive(com.servinglynk.hmis.warehouse.model.staging.Client client);
	
	Client createClient(Client client);
	Client updateClient(Client client);
	void deleteClient(Client client);
	Client getClientById(UUID clientId);
	List<Client> getAllClients(Integer startIndex, Integer maxItems);
	long getClientsCount();
	public com.servinglynk.hmis.warehouse.model.live.Client getClientByDedupCliendId(UUID id);
}
