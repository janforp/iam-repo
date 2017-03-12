package com.zbmatsu.iam.service;

import com.zbmatsu.iam.entity.User;
import com.zbmatsu.iam.exception.NotFoundException;
import com.zbmatsu.iam.exception.ResourceMismatchingException;
import com.zbmatsu.iam.repository.IUserRepository;
import com.zbmatsu.iam.vo.UpdateUserBean;
import com.zbmatsu.iam.vo.UserBean;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


/**
 * Created by Janita on 2016/12/31.
 * 用户相关操作
 */

@Service("userService")
public class UserService {

	@Autowired
	protected IUserRepository userRepository;


	/**
	 * get user by id
	 * @param id
	 * @return
	 */
	public UserBean getUserById(String id) {

		if (!exists(id)){
			throw new NotFoundException("user is not exists");
		}

		User user = userRepository.findOne(id);

		UserBean userBean = new UserBean();
		BeanUtils.copyProperties(user, userBean);

		return userBean;
	}

	/**
	 * create user
	 * @param userBean
	 * @return
	 */
	public UserBean createUser(UserBean userBean){

		String uuid  =  UUID.randomUUID().toString();
		userBean.setId(uuid);
		LocalDateTime current = LocalDateTime.now();
		userBean.setCreationTime(current);
		userBean.setModificationTime(current);
		if(!StringUtils.isEmpty(userBean.getCreateUserId())){//如果用户的创建ID为空时，则表示 用户为自己创建
			userBean.setCreateUserId(uuid);
		}

		User user = new User();
		BeanUtils.copyProperties(userBean, user);

		userRepository.save(user);
		return userBean;
	}

	/**
	 * 根据用户名和密码 查询唯一用户对象
	 * @param userName
	 * @param password
	 * @return
	 */
	public UserBean getUserByUsernameAndPassword(String userName, String password){

		List<User> list = userRepository.getUserByUsernameAndPassword(userName, password);

		UserBean userBean = new UserBean();

		if(list.size() <= 0){
			throw new NotFoundException("user is not exists");
		}else if(list.size() == 1){
			User user = list.get(0);
			BeanUtils.copyProperties(user, userBean);
		}else{
			throw new ResourceMismatchingException("user quantity is not mismatching");
		}
		return  userBean;
	}

	/**
	 * update user
	 * @param userBean
	 * @return
	 */
	public UserBean updateUser(UserBean userBean){

		//设置修改时间
		LocalDateTime current = LocalDateTime.now();
		userBean.setModificationTime(current);

		User user = new User();
		BeanUtils.copyProperties(userBean, user);

		userRepository.save(user);

		return userBean;
	}

	/**
	 * 修改user 多种字段
	 * @param id
	 * @param updateUserBean
	 * @return
	 */
	public UserBean updateUser(String id, UpdateUserBean updateUserBean){

		UserBean userBean = getUserById(id);

		if(StringUtils.isEmpty(updateUserBean)){
			throw new ResourceMismatchingException("resource is not mismatching");
		}

		if(!StringUtils.isEmpty(updateUserBean.getName())){
			userBean.setName(updateUserBean.getName());
		}
		if(!StringUtils.isEmpty(updateUserBean.getAddresses())){
			userBean.setAddresses(updateUserBean.getAddresses());
		}
		if(!StringUtils.isEmpty(updateUserBean.getEmail())){
			userBean.setEmail(updateUserBean.getEmail());
		}
		if(!StringUtils.isEmpty(updateUserBean.getPhoneNumbers())){
			userBean.setPhoneNumbers(updateUserBean.getPhoneNumbers());
		}
		if(!StringUtils.isEmpty(updateUserBean.getPhoto())){
			userBean.setPhoto(updateUserBean.getPhoto());
		}


		return updateUser(userBean);
	}


	/**
	 * delete user
	 * @param id
	 */
	public void deleteUser(String id){

		if (!exists(id)){
			throw new NotFoundException("user is not exists");
		}
		userRepository.delete(id);
	}

	public void batchDeleteUsers(List<Serializable> ids){

		Iterable<User> list = getUsers(ids);
		userRepository.delete(list);
	}


	private Iterable<User> getUsers(List<Serializable> ids){
		return userRepository.findAll(ids);
	}

	/**
	 * 获取所以用户列表
	 * @return
	 */
	public List<UserBean> getAllUser(){

		Iterable<User> list = userRepository.findAll();

		List<UserBean> result = new ArrayList<>();

		list.forEach(user -> {
			UserBean userBean = new UserBean();
			BeanUtils.copyProperties(user, userBean);
			result.add(userBean);
		});

		return result;
	}


	private boolean exists(String id){

		return userRepository.exists(id);
	}
}
