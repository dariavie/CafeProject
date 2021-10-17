package by.training.cafeproject.service.impl;

import by.training.cafeproject.dao.Transaction;
import by.training.cafeproject.dao.TransactionFactory;
import by.training.cafeproject.dao.impl.ServiceInvocationHandlerImpl;
import by.training.cafeproject.exception.PersistentException;
import by.training.cafeproject.service.*;
import by.training.cafeproject.service.exception.ServiceException;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ServiceFactoryImpl implements ServiceFactory {

    private static Logger logger = Logger.getLogger(ServiceFactoryImpl.class);

    private static final Map<Class<? extends Service>, Class<? extends ServiceImpl>> SERVICES = new ConcurrentHashMap<>();

    static {
        SERVICES.put(FoodIngredientService.class, FoodIngredientServiceImpl.class);
        SERVICES.put(FoodService.class, FoodServiceImpl.class);
        SERVICES.put(UserService.class, UserServiceImpl.class);
        SERVICES.put(IngredientService.class, IngredientServiceImpl.class);
        SERVICES.put(OrderService.class, OrderServiceImpl.class);
        SERVICES.put(UserInfoService.class, UserInfoServiceImpl.class);
        SERVICES.put(WorkerService.class, WorkerServiceImpl.class);
        SERVICES.put(OrderFoodService.class, OrderFoodServiceImpl.class);
    }

    private TransactionFactory factory;

    public ServiceFactoryImpl(TransactionFactory factory) throws ServiceException {
        this.factory = factory;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <Type extends Service> Type getService(Class<Type> key) throws ServiceException {
        Class<? extends ServiceImpl> value = SERVICES.get(key);
        if(value != null) {
            try {
                ClassLoader classLoader = value.getClassLoader();
                Class<?>[] interfaces = {key};
                Transaction transaction = factory.createTransaction();
                ServiceImpl service = value.newInstance();
                service.setTransaction(transaction);
                InvocationHandler handler = new ServiceInvocationHandlerImpl(service);
                return (Type) Proxy.newProxyInstance(classLoader, interfaces, handler);
            } catch(PersistentException e) {
                throw new ServiceException(e);
            } catch(InstantiationException | IllegalAccessException e) {
                logger.error("It is impossible to instance service class", e);
                throw new ServiceException(e);
            }
        }
        return null;
    }

    @Override
    public void close() {
        factory.close();
    }
}
