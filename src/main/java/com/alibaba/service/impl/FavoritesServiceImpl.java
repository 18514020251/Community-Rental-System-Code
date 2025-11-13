package com.alibaba.service.impl;

import com.alibaba.pojo.Favorites;
import com.alibaba.mapper.FavoritesMapper;
import com.alibaba.service.IFavoritesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author sister
 * @since 2025-11-13
 */
@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements IFavoritesService {

}
