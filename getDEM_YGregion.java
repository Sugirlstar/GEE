var shpAssetId = 'projects/ee-211301010070/assets/ETYG_Boarder';
var shpFeatureCollection = ee.FeatureCollection(shpAssetId);

// Add the Shapefile boundaries as a vector layer
// Map.addLayer(shpFeatureCollection, {color: '000000'}, 'Shapefile Boundaries');

// Set the map center and zoom level
Map.centerObject(shpFeatureCollection, 12);

print('ok')

// 定义中国云南省和贵州省的区域
var merged_region = ee.Geometry.Polygon([
  [97, 30],
  [110, 30],
  [110, 21],
  [97, 21]
]);

var region = ee.Geometry.Rectangle([97, 30, 110.0, 21]);

// 获取高程数据
var elevation = ee.Image('CGIAR/SRTM90_V4');

print(elevation)

// 裁剪高程数据
var elevation_merged = elevation.clip(merged_region);

// Clip the Landsat image with the Shapefile
// var elevation_merged = elevation_merged.clip(shpFeatureCollection);


// 设置高程地图显示参数
var elevationVis = {
  min: 0,
  max: 7000,
  palette: ['002D78', '16C910', 'D6FE1C', 'F7C01F', 'CE6521'],
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
  value: 'Elevation (meters)',
  style: {
    fontWeight: 'bold',
    fontSize: '18px',
    margin: '0 0 4px 0',
    padding: '0'
  }
});
legend.add(legendTitle);

// 图例颜色和标签
var colors = elevationVis.palette;
var names = ['0', '1000', '2000', '3000', '4000'];

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


// 设置地图显示区域
Map.centerObject(merged_region, 7);

// 降低输出图像的分辨率为500米
var resolution = 500;

// 将图像重投影为指定的分辨率
var demYunnanLowRes = elevation_merged.resample('bilinear').reproject({
  crs: elevation_merged.projection(), // 使用原始投影
  scale: resolution // 设置新的分辨率
});


// 导出地图图层为 GeoTIFF 格式
Export.image.toDrive({
  image: demYunnanLowRes.select('elevation'),
  description: 'DEM_Yunnan_Guizhou',
  scale: 30, 
  region: merged_region
});

// 在地图上绘制高程数据地图
Map.addLayer(demYunnanLowRes, elevationVis, 'Elevation Yunnan and Guizhou');
Map.add(legend);

