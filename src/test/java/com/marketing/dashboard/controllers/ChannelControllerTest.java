package com.marketing.dashboard.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketing.dashboard.entities.Channel;
import com.marketing.dashboard.services.ChannelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ChannelController.class)
@ExtendWith(MockitoExtension.class)
class ChannelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChannelService channelService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllChannels() throws Exception {
        List<Channel> channels = Arrays.asList(new Channel("Facebook"), new Channel("Google"));
        when(channelService.getAllChannels()).thenReturn(channels);

        mockMvc.perform(get("/channels"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetChannelById() throws Exception {
        Channel channel = new Channel("Facebook");
        when(channelService.getChannelById(1L)).thenReturn(channel);

        mockMvc.perform(get("/channels/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Facebook"));
    }

    @Test
    void testCreateChannel() throws Exception {
        Channel channel = new Channel("Instagram");
        when(channelService.createChannel(any(Channel.class))).thenReturn(channel);

        mockMvc.perform(post("/channels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(channel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Instagram"));
    }

    @Test
    void testUpdateChannel() throws Exception {
        Channel updatedChannel = new Channel("Updated Channel");
        when(channelService.updateChannel(eq(1L), any(Channel.class))).thenReturn(updatedChannel);

        mockMvc.perform(put("/channels/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedChannel)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Channel"));
    }

    @Test
    void testDeleteChannel() throws Exception {
        doNothing().when(channelService).deleteChannel(1L);

        mockMvc.perform(delete("/channels/1"))
                .andExpect(status().isOk());

        verify(channelService, times(1)).deleteChannel(1L);
    }
}
