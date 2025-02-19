package com.marketing.dashboard.services;

import com.marketing.dashboard.entities.Channel;
import com.marketing.dashboard.repositories.ChannelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChannelServiceTest {

    @Mock
    private ChannelRepository channelRepository;

    @InjectMocks
    private ChannelService channelService;

    private Channel channel;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        channel = new Channel();
        channel.setChannelId(1L);
        channel.setName("Test Channel");
    }

    @Test
    void testGetAllChannels() {
        List<Channel> channels = Arrays.asList(channel);
        when(channelRepository.findAll()).thenReturn(channels);

        List<Channel> result = channelService.getAllChannels();

        assertEquals(1, result.size());
        assertEquals("Test Channel", result.get(0).getName());
        verify(channelRepository, times(1)).findAll();
    }

    @Test
    void testGetChannelById_HappyPath() {
        when(channelRepository.findById(1L)).thenReturn(Optional.of(channel));

        Channel result = channelService.getChannelById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getChannelId());
        assertEquals("Test Channel", result.getName());
        verify(channelRepository, times(1)).findById(1L);
    }

    @Test
    void testGetChannelById_UnhappyPath() {
        when(channelRepository.findById(1L)).thenReturn(Optional.empty());

        Channel result = channelService.getChannelById(1L);

        assertNull(result);
        verify(channelRepository, times(1)).findById(1L);
    }

    @Test
    void testCreateChannel() {
        when(channelRepository.save(any(Channel.class))).thenReturn(channel);

        Channel result = channelService.createChannel(channel);

        assertNotNull(result);
        assertEquals("Test Channel", result.getName());
        verify(channelRepository, times(1)).save(channel);
    }

    @Test
    void testUpdateChannel_HappyPath() {
        Channel updatedChannel = new Channel();
        updatedChannel.setName("Updated Channel");

        when(channelRepository.findById(1L)).thenReturn(Optional.of(channel));
        when(channelRepository.save(any(Channel.class))).thenReturn(updatedChannel);

        Channel result = channelService.updateChannel(1L, updatedChannel);

        assertNotNull(result);
        assertEquals("Updated Channel", result.getName());
        verify(channelRepository, times(1)).findById(1L);
        verify(channelRepository, times(1)).save(channel);
    }

    @Test
    void testUpdateChannel_UnhappyPath() {
        when(channelRepository.findById(1L)).thenReturn(Optional.empty());

        Channel result = channelService.updateChannel(1L, channel);

        assertNull(result);
        verify(channelRepository, times(1)).findById(1L);
        verify(channelRepository, never()).save(any(Channel.class));
    }

    @Test
    void testDeleteChannel() {
        doNothing().when(channelRepository).deleteById(1L);

        channelService.deleteChannel(1L);

        verify(channelRepository, times(1)).deleteById(1L);
    }
}