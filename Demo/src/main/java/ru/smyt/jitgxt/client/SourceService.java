package ru.smyt.jitgxt.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 *
 * @author Ildar Musin (c) 2012
 */
@RemoteServiceRelativePath("sourceService")
public interface SourceService extends RemoteService {

    public String getSource(String s);
}
