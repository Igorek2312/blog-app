package com.github.igorek2312.blog.app.service;

import com.cloudinary.Api;
import com.cloudinary.Cloudinary;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * @author Igor Rybak
 */
@RunWith(MockitoJUnitRunner.class)
public class CloudinaryStorageServiceTest {
    @Mock
    private Cloudinary cloudinary;
    @Mock
    private Api api;

    private StorageService storageService;

    @Before
    public void setUp() throws Exception {
        Mockito.reset(cloudinary);
        Mockito.reset(api);
        storageService = new CloudinaryStorageService(cloudinary);
    }

    @Test
    public void deleteCloudinaryImageUrl() throws Exception {
        when(cloudinary.api()).thenReturn(api);

        String url = "http://res.cloudinary.com/htbxn2hbz/image/upload/v1497362355/hhwkdvmxicndgfx7rirp.jpg";
        storageService.deleteImageByUrl(url);

        ArgumentCaptor<Iterable> captor = ArgumentCaptor.forClass(Iterable.class);
        verify(api).deleteResources(captor.capture(), anyMap());
        String arg = (String) captor.getValue().iterator().next();
        assertEquals("hhwkdvmxicndgfx7rirp", arg);
    }

    @Test
    public void deleteImageByUrl() throws Exception {
        when(cloudinary.api()).thenReturn(api);
        String url = "https://spring.io/img/spring-by-pivotal.png";
        storageService.deleteImageByUrl(url);
        verifyNoMoreInteractions(api);
    }

    @Test
    public void deleteImageByNullUrl() throws Exception {
        when(cloudinary.api()).thenReturn(api);
        storageService.deleteImageByUrl(null);
        verifyNoMoreInteractions(api);
    }
}