package demo

import com.olvind.mui.StBuildingComponent
import com.olvind.mui.muiIconsMaterial.components as muiIcons
import com.olvind.mui.muiMaterial.colorsMod.{deepOrange, deepPurple}
import com.olvind.mui.muiMaterial.components as mui
import com.olvind.mui.muiMaterial.stylesCreateThemeMod.Theme
import com.olvind.mui.muiSystem.styleFunctionSxStyleFunctionSxMod.SystemCssProperties
import com.olvind.mui.react.mod.{DetailedHTMLProps, HTMLAttributes}
import com.olvind.mui.react.components.div
import org.scalajs.dom.{Element, HTMLDivElement}

import slinky.core._
import slinky.core.facade._

import scala.scalajs.js

object Tabs {
  case class TabPanelProps(index: Int, value: Int, children: List[ReactElement])

  val TabPanel = FunctionalComponent[TabPanelProps] { case TabPanelProps(index, value, children) =>
    div
      .role("tabpanel")
      .hidden(value != index)
      .id(s"simple-tabpanel-$index")(
        if (value == index) {
          Fragment(mui.Box.sx(new SystemCssProperties[Theme] { p = 3 })(mui.Typography(children)))
        } else Fragment()
      )
  }

  def a11yProps(index: Int) =
    new js.Object {
      var id = s"simple-tab-$index"
      var `aria-controls` = s"simple-tabpanel-$index"
    }

  val BasicTabs =
    FunctionalComponent[List[ReactElement]] { children =>
      val (value, setValue) = Hooks.useState(0)
      def handleChange(event: SyntheticEvent[_, _], newValue: Any) =
        setValue(newValue.asInstanceOf[Int])

      mui.Box.sx(new SystemCssProperties[Theme] { width = "100%" })(
        mui.Box.sx(new SystemCssProperties[Theme] { borderBottom = 1; borderColor = "divider" })(
          mui.Tabs
            .value(value)
            .onChange(handleChange)
            .`aria-label`("basic tabs example")(
              mui.Tab.normal.label("Item One").unsafeSpread(a11yProps(0)),
              mui.Tab.normal.label("Item Two").unsafeSpread(a11yProps(1)),
              mui.Tab.normal.label("Item Three").unsafeSpread(a11yProps(2))
            )
        ),
        TabPanel(TabPanelProps(index = 0, value = value, children=children)),
        TabPanel(TabPanelProps(index = 1, value = value, children=children)),
        TabPanel(TabPanelProps(index = 2, value = value, children=children))
      )
    }
}

//import * as React from 'react';
//import Tabs from '@mui/material/Tabs';
//import Tab from '@mui/material/Tab';
//import Typography from '@mui/material/Typography';
//import Box from '@mui/material/Box';
//
//interface TabPanelProps {
//  children?: React.ReactNode;
//  index: number;
//  value: number;
//}
//
//function TabPanel(props: TabPanelProps) {
//  const { children, value, index, ...other } = props;
//
//  return (
//    <div
//      role="tabpanel"
//      hidden={value !== index}
//      id={`simple-tabpanel-${index}`}
//      aria-labelledby={`simple-tab-${index}`}
//      {...other}
//    >
//      {value === index && (
//        <Box sx={{ p: 3 }}>
//          <Typography>{children}</Typography>
//        </Box>
//      )}
//    </div>
//  );
//}
//
//function a11yProps(index: number) {
//  return {
//    id: `simple-tab-${index}`,
//    'aria-controls': `simple-tabpanel-${index}`,
//  };
//}
//
//export default function BasicTabs() {
//  const [value, setValue] = React.useState(0);
//
//  const handleChange = (event: React.SyntheticEvent, newValue: number) => {
//    setValue(newValue);
//  };
//
//  return (
//    <Box sx={{ width: '100%' }}>
//      <Box sx={{ borderBottom: 1, borderColor: 'divider' }}>
//        <Tabs value={value} onChange={handleChange} aria-label="basic tabs example">
//          <Tab label="Item One" {...a11yProps(0)} />
//          <Tab label="Item Two" {...a11yProps(1)} />
//          <Tab label="Item Three" {...a11yProps(2)} />
//        </Tabs>
//      </Box>
//      <TabPanel value={value} index={0}>
//        Item One
//      </TabPanel>
//      <TabPanel value={value} index={1}>
//        Item Two
//      </TabPanel>
//      <TabPanel value={value} index={2}>
//        Item Three
//      </TabPanel>
//    </Box>
//  );
//}
