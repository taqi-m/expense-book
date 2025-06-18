package com.expense.book.view.componenets

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.expense.book.model.data.database.local.entities.Account
import com.expense.book.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccountItem(
    modifier: Modifier,
    account: Account,
    onDeleteAccount: () -> Unit,
    onEditAccount: () -> Unit,
) {
    val accountName = account.accountName
    val accountBalance = "Balance: ${account.currentBalance}"
    var menuExpanded by remember { mutableStateOf(false) }


    // fetching local context
    val mContext = LocalContext.current

    Card(
        modifier = modifier
            .then(
                Modifier
                    .fillMaxWidth()
            ),
        shape = RoundedCornerShape(
            4.dp
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .padding(horizontal = 4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            ) {
                Text(
                    text = accountName,
                )
                HorizontalDivider(
                    thickness = 8.dp,
                    color = Color.Transparent
                )
                Text(
                    text = accountBalance,
                )
            }
            Row(

            ) {
                IconButton(
                    onClick = onEditAccount
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_add_circle_24),
                        contentDescription = "Edit"
                    )
                }
                IconButton(
                    onClick = { menuExpanded = !menuExpanded }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.more_horiz_24),
                        contentDescription = "Edit"
                    )
                }
                // Creating a dropdown menu
                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {

                    // Creating dropdown menu item, on click
                    // would create a Toast message
                    DropdownMenuItem(
                        onClick = {
                            Toast.makeText(mContext, "Edit", Toast.LENGTH_SHORT).show()
                        },
                        text = {
                            Text(text = "Edit")
                        }
                    )

                    // Creating dropdown menu item, on click
                    // would create a Toast message
                    DropdownMenuItem(
                        onClick = {
                            Toast.makeText(
                                mContext,
                                "Delete",
                                Toast.LENGTH_SHORT
                            ).show()
                        },
                        text = {
                            Text(
                                text = "Delete",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    )
                }
            }
        }
    }
}


@Preview(
    showBackground = true
)
@Composable
fun AccountItemPreview() {
    AccountItem(
        modifier = Modifier
            .padding(8.dp),
        account = Account(
            accountName = "Cash",
            description = "Cash Amount",
            currentBalance = 5000.0
        ),
        onDeleteAccount = { },
        onEditAccount = {}
    )
}