package com.gxjtkyy.quality.utils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by gxjtkyy on 2017/8/19.
 */
public class TreeUtil {

//
//    /**
//     * 生成菜单树
//     * @param menus
//     * @return
//     */
//    public static List<MenuVO> createTreeMenus(List<MenuVO> menus) {
//        List<MenuVO> treeMenus = null;
//        if (null != menus && !menus.isEmpty()) {
//            // 创建根节点
//            MenuVO root = new MenuVO();
//            root.setName("菜单根目录");
//
//            // 组装Map数据
//            Map<String, MenuVO> dataMap = new HashMap<String, MenuVO>();
//            for (MenuVO menu : menus) {
//                dataMap.put(menu.getId(), menu);
//            }
//
//            // 组装树形结构
//            Set<Map.Entry<String, MenuVO>> entrySet = dataMap.entrySet();
//            for (Map.Entry<String, MenuVO> entry : entrySet) {
//                MenuVO menu = entry.getValue();
//                if (null == menu.getParentId() || "-1".equals(menu.getParentId())) {
//                    root.getChildrenMenu().add(menu);
//                } else {
//                    dataMap.get(menu.getParentId()).getChildrenMenu().add(menu);
//                }
//            }
//
//            // 对树形结构进行二叉树排序
//            root.sortChildren();
//            treeMenus = root.getChildrenMenu();
//        }
//        return treeMenus;
//    }
}
