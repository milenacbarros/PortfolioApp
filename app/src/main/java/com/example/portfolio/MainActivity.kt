package com.example.portfolio

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.material3.*
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.portfolio.ui.theme.PortfolioTheme

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    object PersonalInfo : Screen("personal_info", "Bio", Icons.Default.Person)
    object Projects : Screen("projects", "Projetos", Icons.Default.Done)
    object Contacts : Screen("contacts", "Contatos", Icons.Default.Call)

}

val screens = listOf(Screen.PersonalInfo, Screen.Projects, Screen.Contacts)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            val (isPersonalInfoSelected, setPersonalInfoSelected) = remember { mutableStateOf(true) }
            val (isProjectsSelected, setProjectsSelected) = remember { mutableStateOf(false) }
            val (isContactsSelected, setContactsSelected) = remember { mutableStateOf(false) }

            PortfolioTheme {
                Scaffold(
                    bottomBar = {
                        BottomNavigation(
                            backgroundColor = Color(0xFFDBCA95)
                        ) {
                            BottomNavigationItem(
                                icon = {
                                    val iconColor = if (isPersonalInfoSelected) Color(0xFFC77E24) else Color.Black
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = null,
                                        tint = iconColor
                                    )
                                },
                                label = {
                                    Text(text = "Bio")
                                },
                                selected = isPersonalInfoSelected,
                                onClick = {
                                    setPersonalInfoSelected(true)
                                    setProjectsSelected(false)
                                    setContactsSelected(false)
                                    navController.navigate(Screen.PersonalInfo.route)
                                }
                            )

                            BottomNavigationItem(
                                icon = {
                                    val iconColor = if (isProjectsSelected) Color(0xFFC77E24) else Color.Black
                                    Icon(
                                        imageVector = Icons.Default.Done,
                                        contentDescription = null,
                                        tint = iconColor
                                    )
                                },
                                label = {
                                    Text(text = "Projetos")
                                },
                                selected = isProjectsSelected,
                                onClick = {
                                    setPersonalInfoSelected(false)
                                    setProjectsSelected(true)
                                    setContactsSelected(false)
                                    navController.navigate(Screen.Projects.route)
                                }
                            )

                            BottomNavigationItem(
                                icon = {
                                    val iconColor = if (isContactsSelected) Color(0xFFC77E24) else Color.Black
                                    Icon(
                                        imageVector = Icons.Default.Call,
                                        contentDescription = null,
                                        tint = iconColor
                                    )
                                },
                                label = {
                                    Text(text = "Contatos")
                                },
                                selected = isContactsSelected,
                                onClick = {
                                    setPersonalInfoSelected(false)
                                    setProjectsSelected(false)
                                    setContactsSelected(true)
                                    navController.navigate(Screen.Contacts.route)
                                }
                            )
                        }
                    }
                ) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.PersonalInfo.route,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Screen.PersonalInfo.route) {
                            PersonalInfoSection()
                        }
                        composable(Screen.Projects.route) {
                            ProjectsScreen()
                        }
                        composable(Screen.Contacts.route) {
                            ContactsScreen()
                        }

                    }
                }
            }
        }
    }
}

@Composable
fun PortfolioContent(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp)

    ) {
        item {
            PersonalInfoSection()
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Button(onClick = { navController.navigate("projects") }) {
                Text("Projetos")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            Button(onClick = { navController.navigate("contacts") }) {
                Text("Contatos")
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun PersonalInfoSection() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Milena de Moraes",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .aspectRatio(1f)
                    .clip(MaterialTheme.shapes.medium)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.milena),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Apaixonada em tecnologia e inovação, busco sempre aprender mais e mais atuando no mercado de trabalho. " +
                        "Aprendendo além da faculdade e com curiosidade a mil sobre novos aspectos da programação. Mulher programadora ainda em ascensão. " +
                        "Buscando sempre mais conhecimento e inovação no ramo da tecnologia, ainda não com tanta experiência mas sempre atrás de novos desafios.",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
data class Project(val title: String, val description: String, val imageRes: Int, val url: String)

val projects = listOf(
    Project(
        title = "Evolução do Portfolio em HTML",
        description = "Clique para saber mais.",
        imageRes = R.drawable.bioiconofc,
        url = "https://github.com/milenacbarros/Web-e-Mobile"
    ),

    )
@Composable
fun ProjectsScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Projetos",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            projects.forEach { project ->
                ProjectItem(project = project)
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
@Composable
fun ContactsScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.back),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Contatos",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Aqui esta algumas plataformas para contato e visualização de projeto",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(8.dp))

            IconWithText(icon = painterResource(id = R.drawable.emailicon), text = "E-mail: milenamcbarros2003@gmail.com", url = "mailto:milenamcbarros2003@gmail.com")
            IconWithText(icon = painterResource(id = R.drawable.telefoneicon), text = "Tel.: +55 81 998123940", url = "tel:+5581998123940")
            IconWithText(icon = painterResource(id = R.drawable.instaicon), text = "Instagram", url = "https://www.instagram.com/milenacbarross/" )
            IconWithText(icon = painterResource(id = R.drawable.githubicon), text = "GitHub", url = "https://github.com/milenacbarros")
            IconWithText(icon = painterResource(id = R.drawable.linkedinicon), text = "Linkedin", url = "https://www.linkedin.com/in/milena-barros-65251a210")
        }
    }
}
@Composable
fun IconWithText(icon: Painter, text: String, url: String) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            openProjectUrl(context, url)
        }

    ) {
        Icon(
            painter = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black 
        )
    }
}
@Composable
fun ProjectItem(project: Project) {
    val context = LocalContext.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                openProjectUrl(context, project.url)
            }
    ) {
        Image(
            painter = painterResource(id = project.imageRes),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .aspectRatio(1f)
                .clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = project.title, style = MaterialTheme.typography.bodyLarge)
            Text(text = project.description, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
fun openProjectUrl(context: android.content.Context, url: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(context, intent, null)
}

@Preview(showBackground = true)
@Composable
fun PortfolioPreview() {
    PortfolioTheme {
        val navController = rememberNavController()
        NavHost(navController, startDestination = "bio") {
            composable("bio") {
                PersonalInfoSection()
            }
            composable("projects") {
                ProjectsScreen()
            }
            composable("contacts") {
                ContactsScreen()
            }
        }
    }
}
