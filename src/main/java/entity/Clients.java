package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Clients {
    @JsonProperty("Client")
    private List<Client> client;

    public Clients() {
    }

    public Clients(List<Client> client) {
        this.client = client;
    }

    public List<Client> getClient() {
        return client;
    }

    public void setClient(List<Client> client) {
        this.client = client;
    }
}
