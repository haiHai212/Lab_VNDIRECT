/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo;

import java.rmi.AlreadyBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public interface Hello extends Remote {

    public String say(String name) throws RemoteException;

}

//class Server implements Hello {
//
//    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
//        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
//        Server obj = new Server();
//        Hello stub = (Hello) UnicastRemoteObject.exportObject(obj, 0);
//        Registry registry = LocateRegistry.getRegistry("127.0.0.1", 8000);
//        registry.bind("Hello", stub);
//        System.out.println("Sever ready");
//        
//    }
//
//    @Override
//    public String say(String name) throws RemoteException {
//        return "Hello " + name;
//    }
//}

