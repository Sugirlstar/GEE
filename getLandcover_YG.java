var shpAssetId = 'projects/ee-211301010070/assets/ETYG_Boarder';
var shpFeatureCollection = ee.FeatureCollection(shpAssetId);

// 定义中国云南省和贵州省的区域
var merged_region = ee.Geometry.Polygon([
  [97, 30],
  [110, 30],
  [110, 20],
  [97, 20]
]);

print(merged_region)

// 获取土地利用类型数据
var landCover = ee.ImageCollection('MODIS/006/MCD12Q1')
                .filterDate('2001-01-01', '2001-12-31')
                .select('LC_Type1')
                .mosaic();
                
var landCover_merged = landCover.clip(merged_region);

// Clip the Landsat image with the Shapefile
// var landCover_merged = landCover_merged.clip(shpFeatureCollection);

// 设置土地利用类型地图显示参数
var landCoverVis = {
  min: 0,
  max: 17,
  palette: ['05450a', '086a10', '54a708', '78d203', '009900', 'c6b044', 'dcd159',
            'dade48', 'fbff13', 'b6ff05', '27ff87', 'c24f44', 'a5a5a5', 'ff6d4c',
            '69fff8', 'f9ffa4', '1c0dff']
};

// 创建图例
var legend = ui.Panel({
  style: {
    position: 'bottom-right',
    padding: '8px',
    backgroundColor: 'white',
  }
});

// 添加图例标题
var legendTitle = ui.Label({
  value: 'Land Cover Classification',
  style: {
    fontWeight: 'bold',
    fontSize: '18px',
    margin: '0 0 4px 0',
    padding: '0'
  }
});
legend.add(legendTitle);

// 图例颜色和标签
var colors = landCoverVis.palette;
var names = ['Evergreen Needleleaf Forest', 'Evergreen Broadleaf Forest', 'Deciduous Needleleaf Forest', 
  'Deciduous Broadleaf Forest', 'Mixed Forest', 'Closed Shrubland', 'Open Shrubland', 'Woody Savanna', 
  'Savanna', 'Grassland', 'Permanent Wetland', 'Cropland', 'Urban and Built-up', 'Cropland/Natural Vegetation Mosaic', 
  'Snow and Ice', 'Barren', 'Water'];

// 添加图例项
for (var i = 0; i < names.length; i++) {
  var colorBox = ui.Label({
    style: {
      backgroundColor: colors[i],
      padding: '8px',
      margin: '0 0 4px 0'
    }
  });

  var description = ui.Label({
    value: names[i],
    style: {margin: '0 0 4px 6px'}
  });

  legend.add(colorBox).add(description);
}

// 在地图上绘制土地利用类型数据地图
Map.addLayer(landCover_merged, landCoverVis, 'Land Cover Yunnan and Guizhou');
Map.add(legend);

// 设置地图显示区域
Map.centerObject(merged_region, 7);

// 导出地图图层为 GeoTIFF 格式
Export.image.toDrive({
  image: landCover_merged,
  description: 'LandCover_Yunnan_Guizhou_MODIS2001',
  scale: 500, // Adjust the scale as needed
  region: merged_region
});

print('sucess')
